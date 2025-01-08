import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getVideoPosts } from 'app/entities/video-post/video-post.reducer';
import { createEntity, getEntity, reset, updateEntity } from './video-tag.reducer';

export const VideoTagUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const videoPosts = useAppSelector(state => state.videoPost.entities);
  const videoTagEntity = useAppSelector(state => state.videoTag.entity);
  const loading = useAppSelector(state => state.videoTag.loading);
  const updating = useAppSelector(state => state.videoTag.updating);
  const updateSuccess = useAppSelector(state => state.videoTag.updateSuccess);

  const handleClose = () => {
    navigate('/video-tag');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getVideoPosts({}));
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

    const entity = {
      ...videoTagEntity,
      ...values,
      posts: mapIdList(values.posts),
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
          ...videoTagEntity,
          posts: videoTagEntity?.posts?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.videoTag.home.createOrEditLabel" data-cy="VideoTagCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.videoTag.home.createOrEditLabel">Create or edit a VideoTag</Translate>
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
                  id="video-tag-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoTag.name')}
                id="video-tag-name"
                name="name"
                data-cy="name"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoTag.code')}
                id="video-tag-code"
                name="code"
                data-cy="code"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoTag.isModerated')}
                id="video-tag-isModerated"
                name="isModerated"
                data-cy="isModerated"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoTag.isDeleted')}
                id="video-tag-isDeleted"
                name="isDeleted"
                data-cy="isDeleted"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoTag.deletionReason')}
                id="video-tag-deletionReason"
                name="deletionReason"
                data-cy="deletionReason"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoTag.mergedWithTagName')}
                id="video-tag-mergedWithTagName"
                name="mergedWithTagName"
                data-cy="mergedWithTagName"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoTag.mergedWithTagCode')}
                id="video-tag-mergedWithTagCode"
                name="mergedWithTagCode"
                data-cy="mergedWithTagCode"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoTag.isActive')}
                id="video-tag-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoTag.createdBy')}
                id="video-tag-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoTag.createdOn')}
                id="video-tag-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoTag.updatedBy')}
                id="video-tag-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoTag.updatedOn')}
                id="video-tag-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoTag.posts')}
                id="video-tag-posts"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/video-tag" replace color="info">
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

export default VideoTagUpdate;
