import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getVideoPosts } from 'app/entities/video-post/video-post.reducer';
import { ChangeType } from 'app/shared/model/enumerations/change-type.model';
import { UrlType } from 'app/shared/model/enumerations/url-type.model';
import { createEntity, getEntity, reset, updateEntity } from './video-post-change-history.reducer';

export const VideoPostChangeHistoryUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const videoPosts = useAppSelector(state => state.videoPost.entities);
  const videoPostChangeHistoryEntity = useAppSelector(state => state.videoPostChangeHistory.entity);
  const loading = useAppSelector(state => state.videoPostChangeHistory.loading);
  const updating = useAppSelector(state => state.videoPostChangeHistory.updating);
  const updateSuccess = useAppSelector(state => state.videoPostChangeHistory.updateSuccess);
  const changeTypeValues = Object.keys(ChangeType);
  const urlTypeValues = Object.keys(UrlType);

  const handleClose = () => {
    navigate('/video-post-change-history');
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
      ...videoPostChangeHistoryEntity,
      ...values,
      post: videoPosts.find(it => it.id.toString() === values.post?.toString()),
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
          changeType: 'ModeratorUpdateContent',
          oldUrlType: 'YouTube',
          ...videoPostChangeHistoryEntity,
          post: videoPostChangeHistoryEntity?.post?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.videoPostChangeHistory.home.createOrEditLabel" data-cy="VideoPostChangeHistoryCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.videoPostChangeHistory.home.createOrEditLabel">
              Create or edit a VideoPostChangeHistory
            </Translate>
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
                  id="video-post-change-history-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.changeType')}
                id="video-post-change-history-changeType"
                name="changeType"
                data-cy="changeType"
                type="select"
              >
                {changeTypeValues.map(changeType => (
                  <option value={changeType} key={changeType}>
                    {translate(`affliateMarketplaceApp.ChangeType.${changeType}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.oldtitle')}
                id="video-post-change-history-oldtitle"
                name="oldtitle"
                data-cy="oldtitle"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.oldDescription')}
                id="video-post-change-history-oldDescription"
                name="oldDescription"
                data-cy="oldDescription"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.oldUrl')}
                id="video-post-change-history-oldUrl"
                name="oldUrl"
                data-cy="oldUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.oldUrlType')}
                id="video-post-change-history-oldUrlType"
                name="oldUrlType"
                data-cy="oldUrlType"
                type="select"
              >
                {urlTypeValues.map(urlType => (
                  <option value={urlType} key={urlType}>
                    {translate(`affliateMarketplaceApp.UrlType.${urlType}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.oldIsAIGenerated')}
                id="video-post-change-history-oldIsAIGenerated"
                name="oldIsAIGenerated"
                data-cy="oldIsAIGenerated"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.oldIsPremium')}
                id="video-post-change-history-oldIsPremium"
                name="oldIsPremium"
                data-cy="oldIsPremium"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.oldIsBlocked')}
                id="video-post-change-history-oldIsBlocked"
                name="oldIsBlocked"
                data-cy="oldIsBlocked"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.oldIsModerated')}
                id="video-post-change-history-oldIsModerated"
                name="oldIsModerated"
                data-cy="oldIsModerated"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.oldIsActive')}
                id="video-post-change-history-oldIsActive"
                name="oldIsActive"
                data-cy="oldIsActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.oldCreatedBy')}
                id="video-post-change-history-oldCreatedBy"
                name="oldCreatedBy"
                data-cy="oldCreatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.oldCreatedOn')}
                id="video-post-change-history-oldCreatedOn"
                name="oldCreatedOn"
                data-cy="oldCreatedOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.oldUpdatedBy')}
                id="video-post-change-history-oldUpdatedBy"
                name="oldUpdatedBy"
                data-cy="oldUpdatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.oldUpdatedOn')}
                id="video-post-change-history-oldUpdatedOn"
                name="oldUpdatedOn"
                data-cy="oldUpdatedOn"
                type="date"
              />
              <ValidatedField
                id="video-post-change-history-post"
                name="post"
                data-cy="post"
                label={translate('affliateMarketplaceApp.videoPostChangeHistory.post')}
                type="select"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/video-post-change-history" replace color="info">
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

export default VideoPostChangeHistoryUpdate;
