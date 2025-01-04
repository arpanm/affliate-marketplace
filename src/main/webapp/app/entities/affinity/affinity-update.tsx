import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getVideoPosts } from 'app/entities/video-post/video-post.reducer';
import { getEntities as getVideoUsers } from 'app/entities/video-user/video-user.reducer';
import { Segment } from 'app/shared/model/enumerations/segment.model';
import { createEntity, getEntity, reset, updateEntity } from './affinity.reducer';

export const AffinityUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const videoPosts = useAppSelector(state => state.videoPost.entities);
  const videoUsers = useAppSelector(state => state.videoUser.entities);
  const affinityEntity = useAppSelector(state => state.affinity.entity);
  const loading = useAppSelector(state => state.affinity.loading);
  const updating = useAppSelector(state => state.affinity.updating);
  const updateSuccess = useAppSelector(state => state.affinity.updateSuccess);
  const segmentValues = Object.keys(Segment);

  const handleClose = () => {
    navigate('/affinity');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getVideoPosts({}));
    dispatch(getVideoUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.score !== undefined && typeof values.score !== 'number') {
      values.score = Number(values.score);
    }

    const entity = {
      ...affinityEntity,
      ...values,
      posts: mapIdList(values.posts),
      users: mapIdList(values.users),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          segment: 'A',
          ...affinityEntity,
          posts: affinityEntity?.posts?.map(e => e.id.toString()),
          users: affinityEntity?.users?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.affinity.home.createOrEditLabel" data-cy="AffinityCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.affinity.home.createOrEditLabel">Create or edit a Affinity</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="affinity-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.affinity.segment')}
                id="affinity-segment"
                name="segment"
                data-cy="segment"
                type="select"
              >
                {segmentValues.map(segment => (
                  <option value={segment} key={segment}>
                    {translate(`affliateMarketplaceApp.Segment.${segment}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.affinity.score')}
                id="affinity-score"
                name="score"
                data-cy="score"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.affinity.isActive')}
                id="affinity-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.affinity.createdBy')}
                id="affinity-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.affinity.createdOn')}
                id="affinity-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.affinity.updatedBy')}
                id="affinity-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.affinity.updatedOn')}
                id="affinity-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.affinity.posts')}
                id="affinity-posts"
                data-cy="posts"
                type="select"
                multiple
                name="posts"
              >
                <option value="" key="0" />
                {videoPosts
                  ? videoPosts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.affinity.users')}
                id="affinity-users"
                data-cy="users"
                type="select"
                multiple
                name="users"
              >
                <option value="" key="0" />
                {videoUsers
                  ? videoUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/affinity" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default AffinityUpdate;
